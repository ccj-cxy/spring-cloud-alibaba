package com.snk.auth.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: 原数据处理
 * @Date: 2020/7/19
 * @author：Cai.ChangJun
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时的填充策略
     * @Author Cai.ChangJun
      * @param metaObject :
     * @return void
     * @throws
     * @Date 2020/7/19 0:51
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        /*this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); */// 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        /* 上面选其一使用,下面的已过时(注意 strictInsertFill 有多个方法,详细查看源码) */
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 更新时的填充策略
     * @Author Cai.ChangJun
      * @param metaObject :
     * @return void
     * @throws
     * @Date 2020/7/19 0:52
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        /*this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); */// 也可以使用(3.3.0 该方法有bug请升级到之后的版本如`3.3.1.8-SNAPSHOT`)
        /* 上面选其一使用,下面的已过时(注意 strictUpdateFill 有多个方法,详细查看源码) */
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
