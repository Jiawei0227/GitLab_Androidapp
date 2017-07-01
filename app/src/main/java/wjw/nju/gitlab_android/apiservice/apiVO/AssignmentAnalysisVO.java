package wjw.nju.gitlab_android.apiservice.apiVO;

import java.util.List;

/**
 * Created by wangjiawei on 2017/6/26.
 */

public class AssignmentAnalysisVO {

    /**
     * studentId : 256
     * assignmentId : 38
     * questionResults : [{"questionId":12,"questionTitle":"Examination-01.git","metricData":{"git_url":"http://219.219.113.227:10080/151250185/Examination-01.git","measured":true,"total_line_count":17,"comment_line_count":2,"field_count":0,"method_count":2,"max_coc":2},"testResult":{"git_url":"http://219.219.113.227:10080/151250185/Examination-01.git","compile_succeeded":true,"tested":true,"testcases":[{"name":"test1","passed":false}]},"scoreResult":{"git_url":"http://219.219.113.227:10080/151250185/Examination-01.git","score":0,"scored":true}},{"questionId":13,"questionTitle":"Examination-02.git","metricData":{"git_url":"http://219.219.113.227:10080/151250185/Examination-02.git","measured":true,"total_line_count":55,"comment_line_count":6,"field_count":0,"method_count":2,"max_coc":8},"testResult":{"git_url":"http://219.219.113.227:10080/151250185/Examination-02.git","compile_succeeded":true,"tested":true,"testcases":[{"name":"test10","passed":false},{"name":"test1","passed":false},{"name":"test2","passed":false},{"name":"test3","passed":false},{"name":"test4","passed":false},{"name":"test5","passed":false},{"name":"test6","passed":false},{"name":"test7","passed":false},{"name":"test8","passed":false},{"name":"test9","passed":false}]},"scoreResult":{"git_url":"http://219.219.113.227:10080/151250185/Examination-02.git","score":0,"scored":true}}]
     */

    private int studentId;
    private int assignmentId;
    private List<QuestionResultsBean> questionResults;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<QuestionResultsBean> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResultsBean> questionResults) {
        this.questionResults = questionResults;
    }

    public static class QuestionResultsBean {
        /**
         * questionId : 12
         * questionTitle : Examination-01.git
         * metricData : {"git_url":"http://219.219.113.227:10080/151250185/Examination-01.git","measured":true,"total_line_count":17,"comment_line_count":2,"field_count":0,"method_count":2,"max_coc":2}
         * testResult : {"git_url":"http://219.219.113.227:10080/151250185/Examination-01.git","compile_succeeded":true,"tested":true,"testcases":[{"name":"test1","passed":false}]}
         * scoreResult : {"git_url":"http://219.219.113.227:10080/151250185/Examination-01.git","score":0,"scored":true}
         */

        private int questionId;
        private String questionTitle;
        private MetricDataBean metricData;
        private TestResultBean testResult;
        private ScoreResultBean scoreResult;

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public String getQuestionTitle() {
            return questionTitle;
        }

        public void setQuestionTitle(String questionTitle) {
            this.questionTitle = questionTitle;
        }

        public MetricDataBean getMetricData() {
            return metricData;
        }

        public void setMetricData(MetricDataBean metricData) {
            this.metricData = metricData;
        }

        public TestResultBean getTestResult() {
            return testResult;
        }

        public void setTestResult(TestResultBean testResult) {
            this.testResult = testResult;
        }

        public ScoreResultBean getScoreResult() {
            return scoreResult;
        }

        public void setScoreResult(ScoreResultBean scoreResult) {
            this.scoreResult = scoreResult;
        }

        public static class MetricDataBean {
            /**
             * git_url : http://219.219.113.227:10080/151250185/Examination-01.git
             * measured : true
             * total_line_count : 17
             * comment_line_count : 2
             * field_count : 0
             * method_count : 2
             * max_coc : 2
             */

            private String git_url;
            private boolean measured;
            private int total_line_count;
            private int comment_line_count;
            private int field_count;
            private int method_count;
            private int max_coc;

            public String getGit_url() {
                return git_url;
            }

            public void setGit_url(String git_url) {
                this.git_url = git_url;
            }

            public boolean isMeasured() {
                return measured;
            }

            public void setMeasured(boolean measured) {
                this.measured = measured;
            }

            public int getTotal_line_count() {
                return total_line_count;
            }

            public void setTotal_line_count(int total_line_count) {
                this.total_line_count = total_line_count;
            }

            public int getComment_line_count() {
                return comment_line_count;
            }

            public void setComment_line_count(int comment_line_count) {
                this.comment_line_count = comment_line_count;
            }

            public int getField_count() {
                return field_count;
            }

            public void setField_count(int field_count) {
                this.field_count = field_count;
            }

            public int getMethod_count() {
                return method_count;
            }

            public void setMethod_count(int method_count) {
                this.method_count = method_count;
            }

            public int getMax_coc() {
                return max_coc;
            }

            public void setMax_coc(int max_coc) {
                this.max_coc = max_coc;
            }
        }

        public static class TestResultBean {
            /**
             * git_url : http://219.219.113.227:10080/151250185/Examination-01.git
             * compile_succeeded : true
             * tested : true
             * testcases : [{"name":"test1","passed":false}]
             */

            private String git_url;
            private boolean compile_succeeded;
            private boolean tested;
            private List<TestcasesBean> testcases;

            public String getGit_url() {
                return git_url;
            }

            public void setGit_url(String git_url) {
                this.git_url = git_url;
            }

            public boolean isCompile_succeeded() {
                return compile_succeeded;
            }

            public void setCompile_succeeded(boolean compile_succeeded) {
                this.compile_succeeded = compile_succeeded;
            }

            public boolean isTested() {
                return tested;
            }

            public void setTested(boolean tested) {
                this.tested = tested;
            }

            public List<TestcasesBean> getTestcases() {
                return testcases;
            }

            public void setTestcases(List<TestcasesBean> testcases) {
                this.testcases = testcases;
            }

            public static class TestcasesBean {
                /**
                 * name : test1
                 * passed : false
                 */

                private String name;
                private boolean passed;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isPassed() {
                    return passed;
                }

                public void setPassed(boolean passed) {
                    this.passed = passed;
                }
            }
        }

        public static class ScoreResultBean {
            /**
             * git_url : http://219.219.113.227:10080/151250185/Examination-01.git
             * score : 0
             * scored : true
             */

            private String git_url;
            private int score;
            private boolean scored;

            public String getGit_url() {
                return git_url;
            }

            public void setGit_url(String git_url) {
                this.git_url = git_url;
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
