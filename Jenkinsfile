pipeline {
    agent any
    stages {
        stage('JunitTest') {
            steps {
                sh "cd $WORKSPACE"
                sh "chmod u+x ./script/TestJunit.sh"
                sh "./script/TestJunit.sh"
            }
        }

        stage('CI') {
            steps {
                    sh "chmod u+x ./script/CI.sh"
                    sh "./script/CI.sh"
            }
        }
    }   
}