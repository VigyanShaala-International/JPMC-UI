package com.vigyanshaala.model.pdfGeneratorModel;

public class RiasecTemplate {

        private  String realistic;
        private  String investigative;
        private  String artistic;
        private  String social;
        private  String enterprising;
        private  String conventional;
        private  String hollandCode;
        private  String studentEmail;

        public RiasecTemplate(){}

        RiasecTemplate(String realistic, String investigative, String artistic, String social, String enterprising, String conventional, String hollandCode, String studentEmail){


        this.realistic = realistic;
        this.investigative = investigative;
        this.artistic = artistic;
        this.social = social;
        this.enterprising = enterprising;
        this.conventional = conventional;
        this.hollandCode = hollandCode;
        this.studentEmail = studentEmail;

        }

        public String getRealistic() {return realistic;}

        public void setRealistic(String realistic) {
            this.realistic = realistic;
        }


        public String getInvestigative() {return investigative;}

        public void setInvestigative(String investigative) {
            this.investigative = investigative;
        }

        public String getArtistic() {return artistic;}

        public void setArtistic(String artistic) {
            this.artistic = artistic;
        }


        public String getSocial() {return social;}

        public void setSocial(String social) {
            this.social = social;
        }

        public String getEnterprising() {return enterprising;}

        public void setEnterprising(String enterprising) {
            this.enterprising = enterprising;
        }


        public String getConventional() {return conventional;}

        public void setConventional(String conventional) {
            this.conventional = conventional;
        }


        public String getHollandCode() {return hollandCode;}

        public void setHollandCode(String hollandCode) {
            this.hollandCode = hollandCode;
        }


        public String getStudentEmail() {
            return studentEmail;
        }

        public void setStudentEmail(String studentEmail) {
            this.studentEmail = studentEmail;
        }


}
