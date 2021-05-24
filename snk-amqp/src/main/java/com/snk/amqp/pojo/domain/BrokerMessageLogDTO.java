package com.snk.amqp.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 可靠性消息投递记录-订单
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BrokerMessageLogDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息唯一ID
     */
    private String messageId;

    /**
     * 消息投递状态  0 投递中 1 投递成功   2 投递失败
     */
    private String status;

    /**
     * 下一次重试时间 或 超时时间
     */
    private Date nextRetry;


}
