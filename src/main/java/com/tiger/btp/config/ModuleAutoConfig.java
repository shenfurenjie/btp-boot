package com.tiger.btp.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@ComponentScan("com.tiger.btp")
@Configuration
public class ModuleAutoConfig {

//    @Bean
//    @ConditionalOnMissingBean(value = ModuleLoader.class)
//    public ModuleLoader getModuleLoader() {
//        ModuleLoader moduleLoader = new ModuleLoaderImpl();
//        return moduleLoader;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(value = ModuleManager.class)
//    public ModuleManager getModuleManager() {
//        ModuleManagerImpl moduleManager = new ModuleManagerImpl();
//        return moduleManager;
//    }

//    @Bean
//    @ConditionalOnMissingBean(value = VersionCenter.class)
//    public VersionCenter getVersionCenter() {
//        return new VersionCenter();
//        VersionCenter center = new VersionCenter(minthaPropertiesService.getJarPluginDir());
//        center.setProductModel(minthaPropertiesService.getVersionCenterProductModel());
//        center.setProductModelBasePath(minthaPropertiesService.getProductModelBasePath());
//
//        Nexus2DownloadPlugin downloadPlugin = new Nexus2DownloadPlugin();
//        downloadPlugin.setMavenSnapshotsRepUrl(minthaPropertiesService.getMavenSnapshotsRepUrl());
//        downloadPlugin.setMavenReleaseRepUrl(minthaPropertiesService.getMavenReleaseRepUrl());
//        center.setDownloadPlugin(downloadPlugin);
//        JarVersionDTO jarVersionDTO = new JarVersionDTO(minthaPropertiesService.getMavenGroupId(),
//                minthaPropertiesService.getMavenArtifactId(),
//                minthaPropertiesService.getMavenVersion());
//        jarVersionDTO.setPriority(999999);
//
//        jarVersionDTO.setScanPackage(minthaPropertiesService.getScanPackage());
//        Map<String, Object> properties = new HashMap();
//        properties.put("git.username", minthaPropertiesService.getGitUsername());
//        properties.put("git.password", minthaPropertiesService.getGitPassword());
//        properties.put("git.repoPath", minthaPropertiesService.getGitRepoPath());
//        properties.put("git.clientPath", minthaPropertiesService.getGitClientPath());
//        properties.put("git.username", minthaPropertiesService.getGitUsername());
//        properties.put("git.branch", minthaPropertiesService.getGitBranch());
//
//
//        //前端git配置
//        properties.put("git.front.username", minthaPropertiesService.getGitFrontUsername());
//        properties.put("git.front.password", minthaPropertiesService.getGitFrontPassword());
//        properties.put("git.front.repoPath", minthaPropertiesService.getGitFrontRepoPath());
//        properties.put("git.front.clientPath", minthaPropertiesService.getGitFrontClientPath());
//        properties.put("git.front.branch", minthaPropertiesService.getGitFrontBranch());
//
//        properties.put("auth.module.name", minthaPropertiesService.getAuthPackageName());
//        properties.put("data.model.defaultDateFormatPattern", minthaPropertiesService.getDefaultDateFormatPattern());
//
//        properties.put("profile", minthaPropertiesService.getProfile());
//
//        jarVersionDTO.setProperties(properties);
//
//        center.registerJarVersion(jarVersionDTO);
//
//        return center;
//    }

//    @Bean
//    @ConditionalOnMissingBean(value = ModuleRefreshScheduler.class)
//    public ModuleRefreshScheduler getJavaModuleRefreshScheduler(VersionCenter versionCenter, ModuleLoader moduleLoader, ModuleManager moduleManager) {
//        ModuleRefreshScheduler moduleRefreshScheduler = new ModuleRefreshScheduler();
//        //versionCenter.setRegisterListener(javaModuleRefreshScheduler);
//        //moduleRefreshScheduler.setVersionCenter(versionCenter);
//        moduleRefreshScheduler.setModuleLoader(moduleLoader);
//        moduleRefreshScheduler.setModuleManager(moduleManager);
//        return moduleRefreshScheduler;
//    }


}
