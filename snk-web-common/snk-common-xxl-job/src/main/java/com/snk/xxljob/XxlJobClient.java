package com.snk.xxljob;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snk.xxljob.config.XxlJobProperties;
import com.snk.xxljob.dto.JobGroupDTO;
import com.snk.xxljob.dto.JobGroupReturnDTO;
import com.snk.xxljob.dto.JobInfoDTO;
import com.snk.xxljob.util.XxlJobUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/10/29
 */
public class XxlJobClient {
    public static Logger logger =  LoggerFactory.getLogger(XxlJobClient.class);

    private XxlJobProperties xxlJobProperties;

    public void setXxlJobProperties(XxlJobProperties xxlJobProperties) {
        this.xxlJobProperties = xxlJobProperties;
    }


    public Integer saveTask (JobInfoDTO jobInfoDTO) {
        //查询列表数据
        try {
            JSONObject response  = XxlJobUtil.addJob(xxlJobProperties.getAdmin().getAddresses(),
                    JSONObject.parseObject(JSONObject.toJSON(jobInfoDTO).toString()));
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                //修改任务参数 把id放入
                // 执行器主键ID
                jobInfoDTO.setExecutorParam( "JobId=" + response.get("content") + ";");
                jobInfoDTO.setId( Integer.valueOf(response.get("content").toString()));
                JSONObject responseUpdate = XxlJobUtil.updateJob(xxlJobProperties.getAdmin().getAddresses(),
                        JSONObject.parseObject(JSONObject.toJSON(jobInfoDTO).toString()));
                if (responseUpdate.containsKey("code") && 200 == (Integer) responseUpdate.get("code")) {
                    //加入任务成功之后直接启动
                    JSONObject responseStart = XxlJobUtil.startJob(xxlJobProperties.getAdmin().getAddresses(), Integer.valueOf(response.get("content").toString()));
                    if (responseStart.containsKey("code") && 200 == (Integer) responseStart.get("code")) {
                        logger.info("添加任务并启动成功！任务id为{}",jobInfoDTO.getId());
                        return jobInfoDTO.getId();
                    } else {
                        logger.error("启动任务失败！任务id为{}",jobInfoDTO.getId());
                        return jobInfoDTO.getId();
                    }
                }
                logger.info("任务更新失败！任务id为{}",jobInfoDTO.getId());
            }
            logger.error("任务新增失败！任务id为{},失败原因：{}",jobInfoDTO.getId(),response.get("msg"));
            return jobInfoDTO.getId();
        }catch (Exception e) {
            logger.error("连接xxl-admin服务端异常");
            return jobInfoDTO.getId();
        }
    }


    public Boolean start(int id) {
        try {
            JSONObject response = XxlJobUtil.startJob(xxlJobProperties.getAdmin().getAddresses(), id);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return true;
            }
            throw new RuntimeException("调用xxl-job-admin-start接口失败！");
        }catch (Exception e) {
            logger.error("连接xxl-admin服务端异常");
            return false;
        }
    }

    public Boolean delete(Integer id) {
        try {
            JSONObject response = XxlJobUtil.deleteJob(xxlJobProperties.getAdmin().getAddresses(), id);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return true;
            }
            throw new RuntimeException("调用xxl-job-admin-delete接口失败！");
        } catch (Exception e) {
            logger.error("连接xxl-admin服务端异常");
            return false;
        }
    }


    public Boolean stop(int id) {
        try {
            JSONObject response = XxlJobUtil.stopJob(xxlJobProperties.getAdmin().getAddresses(), id);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return true;
            }
            throw new Exception("调用xxl-job-admin-stop接口失败！");
        } catch (Exception e) {
            logger.error("连接xxl-admin服务端异常");
            return false;
        }
    }

    public Boolean login() {
        try {
            String cookie = XxlJobUtil.login(xxlJobProperties.getAdmin().getAddresses(), xxlJobProperties.getAdmin().getUsername()
                    , xxlJobProperties.getAdmin().getPassword());
            if (StrUtil.isNotBlank(cookie)) {
                return true;
            }
            throw new Exception("调用xxl-job-admin-login接口失败！");
        } catch (Exception e) {
            logger.error("连接xxl-admin服务端异常");
            return false;
        }
    }

    public JobGroupDTO getAppNameIdByAppname() {
        try {
            JSONObject response = XxlJobUtil.getAppNameIdByAppname(xxlJobProperties.getAdmin().getAddresses()
                    ,xxlJobProperties.getExecutor().getAppname());
            String jsonString = JSON.toJSONString(response);
            TypeReference<JobGroupReturnDTO> list =new TypeReference<JobGroupReturnDTO>(){};
            Type t = list.getType();
            JobGroupReturnDTO jobGroupReturnDTO = JSONUtil.toBean(jsonString, t, false);
            if (jobGroupReturnDTO.getRecordsTotal()>0) {
                return jobGroupReturnDTO.getData().get(0);
            }
                throw new Exception("调用xxl-job-admin-getAppNameIdByAppname接口失败！");
        } catch (Exception e) {
            return new JobGroupDTO();
        }
    }

}
