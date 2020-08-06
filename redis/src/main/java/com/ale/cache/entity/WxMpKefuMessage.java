package com.ale.cache.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMpKefuMessage implements Serializable {

        private static final long serialVersionUID = -9196732086954365246L;

        private String toUser;
        private String msgType;
        private String content;
        private String mediaId;
        private String thumbMediaId;
        private String title;
        private String description;
        private String musicUrl;
        private String hqMusicUrl;
        private String kfAccount;
        private String cardId;
        private String mpNewsMediaId;
        private String miniProgramAppId;
        private String miniProgramPagePath;
        private String headContent;
        private String tailContent;

}
