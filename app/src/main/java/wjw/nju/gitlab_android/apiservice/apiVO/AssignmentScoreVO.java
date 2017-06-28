package wjw.nju.gitlab_android.apiservice.apiVO;

import java.util.List;

/**
 * Created by wangjiawei on 2017/6/28.
 */

public class AssignmentScoreVO {

    /**
     * assignmentId : 12
     * questions : [{"questionInfo":{"id":1,"title":"题目1","description":"xxxxx","type":"exam"},"students":[{"studentId":227,"studentName":"zz","studentNumber":"141","score":100,"scored":true}]}]
     */

    private int assignmentId;
    private List<QuestionsBean> questions;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean {
        /**
         * questionInfo : {"id":1,"title":"题目1","description":"xxxxx","type":"exam"}
         * students : [{"studentId":227,"studentName":"zz","studentNumber":"141","score":100,"scored":true}]
         */

        private QuestionInfoBean questionInfo;
        private List<StudentsBean> students;

        public QuestionInfoBean getQuestionInfo() {
            return questionInfo;
        }

        public void setQuestionInfo(QuestionInfoBean questionInfo) {
            this.questionInfo = questionInfo;
        }

        public List<StudentsBean> getStudents() {
            return students;
        }

        public void setStudents(List<StudentsBean> students) {
            this.students = students;
        }

        public static class QuestionInfoBean {
            /**
             * id : 1
             * title : 题目1
             * description : xxxxx
             * type : exam
             */

            private int id;
            private String title;
            private String description;
            private String type;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class StudentsBean {
            /**
             * studentId : 227
             * studentName : zz
             * studentNumber : 141
             * score : 100
             * scored : true
             */

            private int studentId;
            private String studentName;
            private String studentNumber;
            private int score;
            private boolean scored;

            public int getStudentId() {
                return studentId;
            }

            public void setStudentId(int studentId) {
                this.studentId = studentId;
            }

            public String getStudentName() {
                return studentName;
            }

            public void setStudentName(String studentName) {
                this.studentName = studentName;
            }

            public String getStudentNumber() {
                return studentNumber;
            }

            public void setStudentNumber(String studentNumber) {
                this.studentNumber = studentNumber;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public boolean isScored() {
                return scored;
            }

            public void setScored(boolean scored) {
                this.scored = scored;
            }
        }
    }
}
