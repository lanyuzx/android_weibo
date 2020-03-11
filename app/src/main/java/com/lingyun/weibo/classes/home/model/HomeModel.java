package com.lingyun.weibo.classes.home.model;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class HomeModel {

    private List<HomeStatusesModel> statuses;

    private List advertises;

    private List ad;

    private Long total_number;



    class  HomeStatusesModel {
        private Map visible;
        private String created_at;
        private  Long id;
        private  Long idstr;
        private  Long mid;
        private  int edit_count;
        private  Boolean can_edit;
        private  String edit_at;
        private  String  version;

        private  String text;
        private  Long textLength;
        private  String source;
        private   Boolean favorited;
        private  Boolean truncated;
        private Object pic_urls;

        private String thumbnail_pic;
        private  String bmiddle_pic;
        private String original_pic;

        private HomeUserModel user;


        class HomeUserModel {

            private Long id;
            private Long idstr;
//            private Long class;
            private String screen_name;
            private String name;
            private String province;
            private String city;
            private String location;
            private String description;
            private String url;
            private String profile_image_url;
            private String cover_image;
            private String cover_image_phone;
            private String profile_url;
            private String domain;
            private String gender;
            private Long followers_count;
            private Long friends_count;
            private Long pagefriends_count;
            private Long statuses_count;
            private Long video_status_count;
            private Long favourites_count;
            private String avatar_large;
            private String avatar_hd;
            private String verified_reason;


            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Long getIdstr() {
                return idstr;
            }

            public void setIdstr(Long idstr) {
                this.idstr = idstr;
            }

            public String getScreen_name() {
                return screen_name;
            }

            public void setScreen_name(String screen_name) {
                this.screen_name = screen_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getProfile_image_url() {
                return profile_image_url;
            }

            public void setProfile_image_url(String profile_image_url) {
                this.profile_image_url = profile_image_url;
            }

            public String getCover_image() {
                return cover_image;
            }

            public void setCover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public String getCover_image_phone() {
                return cover_image_phone;
            }

            public void setCover_image_phone(String cover_image_phone) {
                this.cover_image_phone = cover_image_phone;
            }

            public String getProfile_url() {
                return profile_url;
            }

            public void setProfile_url(String profile_url) {
                this.profile_url = profile_url;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public Long getFollowers_count() {
                return followers_count;
            }

            public void setFollowers_count(Long followers_count) {
                this.followers_count = followers_count;
            }

            public Long getFriends_count() {
                return friends_count;
            }

            public void setFriends_count(Long friends_count) {
                this.friends_count = friends_count;
            }

            public Long getPagefriends_count() {
                return pagefriends_count;
            }

            public void setPagefriends_count(Long pagefriends_count) {
                this.pagefriends_count = pagefriends_count;
            }

            public Long getStatuses_count() {
                return statuses_count;
            }

            public void setStatuses_count(Long statuses_count) {
                this.statuses_count = statuses_count;
            }

            public Long getVideo_status_count() {
                return video_status_count;
            }

            public void setVideo_status_count(Long video_status_count) {
                this.video_status_count = video_status_count;
            }

            public Long getFavourites_count() {
                return favourites_count;
            }

            public void setFavourites_count(Long favourites_count) {
                this.favourites_count = favourites_count;
            }

            public String getAvatar_large() {
                return avatar_large;
            }

            public void setAvatar_large(String avatar_large) {
                this.avatar_large = avatar_large;
            }

            public String getAvatar_hd() {
                return avatar_hd;
            }

            public void setAvatar_hd(String avatar_hd) {
                this.avatar_hd = avatar_hd;
            }

            public String getVerified_reason() {
                return verified_reason;
            }

            public void setVerified_reason(String verified_reason) {
                this.verified_reason = verified_reason;
            }
        }


        public Map getVisible() {
            return visible;
        }

        public void setVisible(Map visible) {
            this.visible = visible;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getIdstr() {
            return idstr;
        }

        public void setIdstr(Long idstr) {
            this.idstr = idstr;
        }

        public Long getMid() {
            return mid;
        }

        public void setMid(Long mid) {
            this.mid = mid;
        }

        public int getEdit_count() {
            return edit_count;
        }

        public void setEdit_count(int edit_count) {
            this.edit_count = edit_count;
        }

        public Boolean getCan_edit() {
            return can_edit;
        }

        public void setCan_edit(Boolean can_edit) {
            this.can_edit = can_edit;
        }

        public String getEdit_at() {
            return edit_at;
        }

        public void setEdit_at(String edit_at) {
            this.edit_at = edit_at;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Long getTextLength() {
            return textLength;
        }

        public void setTextLength(Long textLength) {
            this.textLength = textLength;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public Boolean getFavorited() {
            return favorited;
        }

        public void setFavorited(Boolean favorited) {
            this.favorited = favorited;
        }

        public Boolean getTruncated() {
            return truncated;
        }

        public void setTruncated(Boolean truncated) {
            this.truncated = truncated;
        }

        public Object getPic_urls() {
            return pic_urls;
        }

        public void setPic_urls(Object pic_urls) {
            this.pic_urls = pic_urls;
        }

        public String getThumbnail_pic() {
            return thumbnail_pic;
        }

        public void setThumbnail_pic(String thumbnail_pic) {
            this.thumbnail_pic = thumbnail_pic;
        }

        public String getBmiddle_pic() {
            return bmiddle_pic;
        }

        public void setBmiddle_pic(String bmiddle_pic) {
            this.bmiddle_pic = bmiddle_pic;
        }

        public String getOriginal_pic() {
            return original_pic;
        }

        public void setOriginal_pic(String original_pic) {
            this.original_pic = original_pic;
        }

        public HomeUserModel getUser() {
            return user;
        }

        public void setUser(HomeUserModel user) {
            this.user = user;
        }
    }


    public List<HomeStatusesModel> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<HomeStatusesModel> statuses) {
        this.statuses = statuses;
    }

    public List getAdvertises() {
        return advertises;
    }

    public void setAdvertises(List advertises) {
        this.advertises = advertises;
    }

    public List getAd() {
        return ad;
    }

    public void setAd(List ad) {
        this.ad = ad;
    }

    public Long getTotal_number() {
        return total_number;
    }

    public void setTotal_number(Long total_number) {
        this.total_number = total_number;
    }
}

