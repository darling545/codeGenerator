package com.dong.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;

import cn.hutool.json.JSONUtil;

/**
 * 单例设计模式（确保元数据对象只有一个）
 */
public class MetaManager {

    private static volatile Meta meta;

    private MetaManager() {
        // 防止被外部实例化
    }

    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta(){
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        MetaValidator.doValidAndFill(newMeta);
        return newMeta;
    }

}
