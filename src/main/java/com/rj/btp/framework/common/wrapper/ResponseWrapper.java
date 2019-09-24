package com.rj.btp.framework.common.wrapper;

import com.google.common.base.Throwables;
import com.rj.btp.framework.common.enums.ErrorCodeEnum;
import com.rj.btp.framework.common.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * response包装类
 *
 * @author Caratacus
 */
@Slf4j
public class ResponseWrapper extends HttpServletResponseWrapper {

    private ErrorCodeEnum errorCodeEnum;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public ResponseWrapper(HttpServletResponse response, ErrorCodeEnum errorCodeEnum) {
        super(response);
        setErrorCodeEnum(errorCodeEnum);
    }

    /**
     * 获取ErrorCode
     *
     * @return
     */
    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    /**
     * 设置ErrorCode
     *
     * @param errorCodeEnum
     */
    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        if (Objects.nonNull(errorCodeEnum)) {
            this.errorCodeEnum = errorCodeEnum;
            super.setStatus(errorCodeEnum.code());
        }
    }

    /**
     * 向外输出错误信息
     *
     * @param e
     * @throws IOException
     */
    public void writerErrorMsg(Exception e) {
        if (Objects.isNull(errorCodeEnum)) {
            log.warn("Warn: ErrorCodeEnum cannot be null, Skip the implementation of the method.");
            return;
        }
        printWriterApiResponses(RestResponse.failure(this.getErrorCodeEnum().code(), e.getMessage()));
    }

    /**
     * 设置ApiErrorMsg
     */
    public void writerErrorMsg() {
        writerErrorMsg(null);
    }

    /**
     * 向外输出ApiResponses
     *
     * @param restResponse
     */
    public void printWriterApiResponses(RestResponse restResponse) {
        writeValueAsJson(restResponse);
    }

    /**
     * 向外输出json对象
     *
     * @param obj
     */
    public void writeValueAsJson(Object obj) {
        if (super.isCommitted()) {
            log.warn("Warn: Response isCommitted, Skip the implementation of the method.");
            return;
        }
        super.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        super.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (PrintWriter writer = super.getWriter()) {
            writer.print(JacksonUtil.toJson(obj));
            writer.flush();
        } catch (IOException e) {
            log.warn("Error: Response printJson faild, stackTrace: {}", Throwables.getStackTraceAsString(e));
        }
    }

}
