package com.github.gkislin.common.web;

import com.github.gkislin.common.LoggerWrapper;
import com.github.gkislin.common.LoggingLevel;
import com.github.gkislin.common.io.IOUtil;
import com.github.gkislin.common.util.Util;
import com.sun.xml.ws.api.handler.MessageHandlerContext;
import com.sun.xml.ws.api.message.Message;
import com.sun.xml.ws.api.streaming.XMLStreamWriterFactory;

import javax.xml.stream.XMLStreamWriter;
import java.util.Map;

/**
 * User: GKislin
 * Date: 27.04.2009
 * <p/>
 * Refactored from:
 *
 * @see {http://weblogs.java.net/blog/ramapulavarthi/archive/2007/12/extend_your_web.html
 *      http://fisheye5.cenqua.com/browse/jax-ws-sources/jaxws-ri/samples/efficient_handler/src/efficient_handler/common/LoggingHandler.java?r=MAIN}
 *      <p/>
 *      This simple LoggingHandler will log the contents of incoming
 *      and outgoing messages. This is implemented as a MessageHandler
 *      for better performance over SOAPHandler.
 */

public abstract class SoapLoggingHandler extends SoapBaseHandler {

    private static final LoggerWrapper LOGGER = LoggerWrapper.get(SoapLoggingHandler.class);
    private final LoggingLevel loggingLevel;

    protected SoapLoggingHandler(LoggingLevel loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    private static final Map<LoggingLevel, HANDLER> HANDLER_MAP =
            Util.asEnumMap(LoggingLevel.class,
                    LoggingLevel.TRACE, HANDLER.DEBUG,
                    LoggingLevel.DEBUG, HANDLER.DEBUG,
                    LoggingLevel.INFO, HANDLER.INFO,
                    LoggingLevel.WARN, HANDLER.ERROR,
                    LoggingLevel.ERROR, HANDLER.ERROR,
                    LoggingLevel.FATAL, HANDLER.NONE);

    protected enum HANDLER {
        NONE {
            @Override
            public void handleFault(MessageHandlerContext mhc) {
            }

            @Override
            public void handleMessage(MessageHandlerContext mhc, boolean isRequest) {
            }
        },
        ERROR {
            private static final String REQUEST_MSG = "REQUEST_MSG";

            public void handleFault(MessageHandlerContext context) {
                LOGGER.error("Fault SOAP request:\n" + getMessageText(((Message) context.get(REQUEST_MSG))));
            }

            public void handleMessage(MessageHandlerContext context, boolean isRequest) {
                if (isRequest) {
                    context.put(REQUEST_MSG, context.getMessage().copy());
                }
            }
        },
        INFO {
            public void handleFault(MessageHandlerContext context) {
                ERROR.handleFault(context);
            }

            public void handleMessage(MessageHandlerContext context, boolean isRequest) {
                ERROR.handleMessage(context, isRequest);
                LOGGER.info((isRequest ? "SOAP request: " : "SOAP response: ") + context.getMessage().getPayloadLocalPart());
            }
        },
        DEBUG {
            public void handleFault(MessageHandlerContext context) {
                LOGGER.error("Fault SOAP message:\n" + getMessageText(context.getMessage().copy()));
            }

            public void handleMessage(MessageHandlerContext context, boolean isRequest) {
                LOGGER.info((isRequest ? "SOAP request:\n" : "SOAP response:\n") + getMessageText(context.getMessage().copy()));
            }
        };

        public abstract void handleMessage(MessageHandlerContext mhc, boolean isRequest);

        public abstract void handleFault(MessageHandlerContext mhc);

        protected static String getMessageText(Message msg) {
            try {
                IOUtil.StringOutputStream out = new IOUtil.StringOutputStream();
                XMLStreamWriter writer = XMLStreamWriterFactory.create(out, "UTF-8");
//                IndentingXMLStreamWriter wrap = new IndentingXMLStreamWriter(writer);
                msg.writeTo(writer);
                return out.toString();
            } catch (Exception e) {
                LOGGER.warn("Coudn't get SOAP message for logging", e);
                return null;
            }
        }
    }

    abstract protected boolean isRequest(boolean isOutbound);

    public boolean handleMessage(MessageHandlerContext mhc) {
        HANDLER_MAP.get(loggingLevel).handleMessage(mhc, isRequest(isOutbound(mhc)));
        return true;
    }

    public boolean handleFault(MessageHandlerContext mhc) {
        HANDLER_MAP.get(loggingLevel).handleFault(mhc);
        return true;
    }
}
