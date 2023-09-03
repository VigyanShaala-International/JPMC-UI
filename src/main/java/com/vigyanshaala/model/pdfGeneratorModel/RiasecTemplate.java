package com.vigyanshaala.model.pdfGeneratorModel;

public class RiasecTemplate {

        private  String realisticScore;
        private  String investigativeScore;
        private  String artisticScore;
        private  String socialScore;
        private  String enterprisingScore;
        private  String conventionalScore;
        private  String firstInterest;
        private  String secondInterest;
        private  String thirdInterest;
        private  String studentEmail;

        public RiasecTemplate(){}

    RiasecTemplate(String realisticScore, String investigativeScore, String artisticScore, String socialScore, String enterprisingScore
            ,String conventionalScore, String firstInterest, String secondInterest, String thirdInterest, String studentEmail){


        this.realisticScore = realisticScore;
        this.investigativeScore = investigativeScore;
        this.artisticScore = artisticScore;
        this.socialScore = socialScore;
        this.enterprisingScore = enterprisingScore;
        this.conventionalScore = conventionalScore;
        this.firstInterest = firstInterest;
        this.secondInterest = secondInterest;
        this.thirdInterest = thirdInterest;
        this.studentEmail = studentEmail;

        }

        public String getRealisticScore() {return realisticScore;}

        public void setRealisticScore(String realisticScore) {
            this.realisticScore = realisticScore;
        }


        public String getInvestigativeScore() {return investigativeScore;}

        public void setInvestigativeScore(String investigativeScore) {
            this.investigativeScore = investigativeScore;
        }

        public String getArtisticScore() {return artisticScore;}

        public void setArtisticScore(String artisticScore) {
            this.realisticScore = artisticScore;
        }


        public String getSocialScore() {return socialScore;}

        public void setSocialScore(String socialScore) {
            this.realisticScore = socialScore;
        }

        public String getEnterprisingScore() {return enterprisingScore;}

        public void setEnterprisingScore(String enterprisingScore) {
            this.realisticScore = enterprisingScore;
        }


        public String getConventionalScore() {return conventionalScore;}

        public void setConventionalScore(String conventionalScore) {
            this.realisticScore = conventionalScore;
        }


        public String getFirstInterest() {return firstInterest;}

        public void setFirstInterest(String firstInterest) {
            this.realisticScore = firstInterest;
        }

        public String getSecondInterest() {return secondInterest;}

        public void setSecondInterest(String secondInterest) {
            this.realisticScore = secondInterest;
        }


        public String getThirdInterest() {return thirdInterest;}

        public void setThirdInterest(String thirdInterest) {
            this.realisticScore = thirdInterest;
        }


        public String getStudentEmail() {
            return studentEmail;
        }

        public void setStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
        }


}
