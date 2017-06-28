package wjw.nju.gitlab_android.apiservice.apiVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangjiawei on 2017/6/3.
 */

public class PublishTaskVO implements Serializable {

    /**
     * id : 3
     * title : 考试1
     * description : 考试1
     * startAt : 2017-04-25 16:22:47.0
     * endAt : 2017-04-25 16:46:47.0
     * questions : [{"id":1,"title":"题目1","description":"题目1","difficulty":"3","gitUrl":"http://115.29.184.56:10080/Mooc-CSE-I-2017/Exercise013-IsPalindrome.git","type":"exam","creator":{"id":1,"username":"liuqin","name":"刘钦","type":"teacher","avatar":null,"gender":"male","email":"lq@nju.edu.cn","schoolId":1},"duration":0,"link":-1,"knowledgeVos":null}]
     * course : 1
     * status : analyzingFinish
     * currentTime : 2017-06-03 15:46:20
     */

    private int id;
    private String title;
    private String description;
    private String startAt;
    private String endAt;
    private int course;
    private String status;
    private String currentTime;
    private List<QuestionsBean> questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean implements Serializable {
        /**
         * id : 1
         * title : 题目1
         * description : 题目1
         * difficulty : 3
         * gitUrl : http://115.29.184.56:10080/Mooc-CSE-I-2017/Exercise013-IsPalindrome.git
         * type : exam
         * creator : {"id":1,"username":"liuqin","name":"刘钦","type":"teacher","avatar":null,"gender":"male","email":"lq@nju.edu.cn","schoolId":1}
         * duration : 0
         * link : -1
         * knowledgeVos : null
         */

        private int id;
        private String title;
        private String description;
        private String difficulty;
        private String gitUrl;
        private String type;
        private CreatorBean creator;
        private int duration;
        private int link;
        private Object knowledgeVos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getGitUrl() {
            return gitUrl;
        }

        public void setGitUrl(String gitUrl) {
            this.gitUrl = gitUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getLink() {
            return link;
        }

        public void setLink(int link) {
            this.link = link;
        }

        public Object getKnowledgeVos() {
            return knowledgeVos;
        }

        public void setKnowledgeVos(Object knowledgeVos) {
            this.knowledgeVos = knowledgeVos;
        }

        public static class CreatorBean implements Serializable {
            /**
             * id : 1
             * username : liuqin
             * name : 刘钦
             * type : teacher
             * avatar : null
             * gender : male
             * email : lq@nju.edu.cn
             * schoolId : 1
             */

            private int id;
            private String username;
            private String name;
            private String type;
            private Object avatar;
            private String gender;
            private String email;
            private int schoolId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }
        }
    }
}
