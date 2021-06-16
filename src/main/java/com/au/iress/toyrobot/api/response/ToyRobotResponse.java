package com.au.iress.toyrobot.api.response;

public class ToyRobotResponse {
        private int errorCode;
        private String errorMessage;
        private String position;


        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
}
