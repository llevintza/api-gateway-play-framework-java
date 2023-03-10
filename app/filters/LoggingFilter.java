package filters;

import akka.stream.Materializer;
import dto.logging.RequestLoggingData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static net.logstash.logback.argument.StructuredArguments.kv;

public class LoggingFilter extends Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Inject
    public LoggingFilter(Materializer materializer) {
        super(materializer);
    }

    @Override
    public CompletionStage<Result> apply(
            Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
            Http.RequestHeader requestHeader) {
        long startTime = System.currentTimeMillis();
        logger.info("Request Received");
        return nextFilter
                .apply(requestHeader)
                .thenApply(
                        result -> {
                            long endTime = System.currentTimeMillis();
                            long requestTime = endTime - startTime;

                            logger.info(
                                    "{} {} took {}ms and returned {}",
                                    requestHeader.method(),
                                    requestHeader.uri(),
                                    requestTime,
                                    result.status()
                            );

                            logger.info(
                                    "Request processed ",
                                    kv(
                                            "resultData", new RequestLoggingData() {{
                                                setMethod(requestHeader.method());
                                                setUri(requestHeader.uri());
                                                setStatus(result.status());
                                                setDuration(requestTime);
                                            }}
                                    )
                            );

                            return result.withHeader("Request-Time", "" + requestTime);
                        });
    }
}
