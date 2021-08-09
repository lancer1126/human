/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.lance.config;

import com.lance.utils.HumanConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /** 文件大小限制 */
    private Long maxSize;

    /** 头像大小限制 */
    private Long avatarMaxSize;

    private HuPath mac;

    private HuPath linux;

    private HuPath windows;

    public HuPath getPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith(HumanConstant.WIN)) {
            return windows;
        } else if(os.toLowerCase().startsWith(HumanConstant.MAC)){
            return mac;
        }
        return linux;
    }

    @Data
    public static class HuPath {

        private String path;

        private String avatar;
    }
}
