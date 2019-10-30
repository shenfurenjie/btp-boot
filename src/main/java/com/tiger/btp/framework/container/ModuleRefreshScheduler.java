package com.tiger.btp.framework.container;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.tiger.btp.jarslink.api.ModuleConfig;
import com.tiger.btp.jarslink.api.impl.AbstractModuleRefreshScheduler;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;

/**
 * 调度器在bean初始化的时候会启动一个调度任务，每分钟刷新一次模块，如果模块的版本号发生变更则会更新模块。
 * 实现这个方法时，必须把模块（jar包）下载到机器本地，模块的配置信息说明如下：
 */
@Slf4j
public class ModuleRefreshScheduler extends AbstractModuleRefreshScheduler {


    @Getter
    @Setter
    VersionCenter versionCenter;

    /**
     * 获取模块配置信息
     *
     * @return
     */
    @Override
    public List<ModuleConfig> queryModuleConfigs() {
        URL demoModule = Thread.currentThread().getContextClassLoader().getResource("META-INF/jars/demo-btp-1.0-SNAPSHOT.jar");
        ModuleConfig moduleConfig = new ModuleConfig();
        moduleConfig.setName("demo-btp");
        moduleConfig.setEnabled(true);
        moduleConfig.setVersion("1.0-SNAPSHOT");
        moduleConfig.setProperties(ImmutableMap.of("svnPath", new Object()));
        moduleConfig.setModuleUrl(ImmutableList.of(demoModule));
        return ImmutableList.of(moduleConfig);
    }
}
