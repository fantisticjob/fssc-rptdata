package com.longfor.fsscreportdb.utils;

import java.io.InputStream;

import org.pentaho.di.cluster.SlaveServer;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobExecutionConfiguration;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.www.SlaveServerJobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class KettleUtilRemote {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
    public  int  callRemote(String host, String port, String jobFileName) {
    	
    	int result = 200;
    	
    	log.info("host{}" , host);
    	log.info("port{}" , port);
    	log.info("jobFileName{}" , jobFileName);
    	
        try {
        	
            KettleEnvironment.init();
            SlaveServer remoteSlaveServer = new SlaveServer();
            remoteSlaveServer.setHostname(host);// 设置远程IP
            remoteSlaveServer.setPort(port);// 端口
            
            remoteSlaveServer.setUsername("cluster");
            remoteSlaveServer.setPassword("cluster");
            
            // FileSystemResource r = new FileSystemResource(jobFileName);
            log.info("getResourceFromClass - time1 {} ");
            InputStream stream
                    = getClass().getClassLoader().getResourceAsStream(jobFileName);
            if (stream == null) {
                log.info("kjb stream is emplty, try again - time2 {} ");
                stream = this.getClass().getResourceAsStream(jobFileName);
            }

            if (stream == null) {
                log.info("kjb stream cannot be read propertly, stop processing{} ");
                result = -2;
                log.info("oneResult=-2");
            } else {
                // jobname 是Job脚本的路径及名称
                JobMeta jobMeta = new JobMeta(stream, null, null);
                //========================add by zahoxin===========
                // 设置参数
                // jobMeta.setParameterValue("BATCHNUM", "4016");
                Job job = new Job(null,jobMeta);
                jobMeta.setInternalKettleVariables(job);
                job.setVariable("BATCHNUM", "4016");
                //========================add by zahoxin===========
                JobExecutionConfiguration jobExecutionConfiguration = new JobExecutionConfiguration();
                jobExecutionConfiguration.setRemoteServer(remoteSlaveServer);// 配置远程服务

                String lastCarteObjectId = Job.sendToSlaveServer(jobMeta, jobExecutionConfiguration, null, null);
                // job.sendToSlaveServer(jobMeta, jobExecutionConfiguration, null, null);

                log.info("lastCarteObjectId={}" , lastCarteObjectId);

                SlaveServerJobStatus jobStatus = null;

                do {
                    Thread.sleep(5000);
                    jobStatus = remoteSlaveServer.getJobStatus(jobMeta.getName(), lastCarteObjectId, 0);

                } while (jobStatus != null && jobStatus.isRunning());

                Result oneResult = new Result();

                System.out.println(jobStatus);
                if (jobStatus!=null && jobStatus.getResult() != null) {

                    // 流程完成，得到结果
                    oneResult = jobStatus.getResult();
                    log.info("oneResult={}" , oneResult.getExitStatus());

                    return 200;

                } else {
                    result = -1;
                    log.info("oneResult=-1");
                }
            }
        } catch (Exception e1) {
        	log.info("e1={}" , e1.getStackTrace().toString());
            e1.printStackTrace();
        	result = -2;
        	log.info("oneResult=-2");
        }
        
        return result;
    }
}
