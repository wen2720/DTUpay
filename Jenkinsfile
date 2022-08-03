pipeline {
    agent any
    stages {
        stage('JunitTest') {
            steps {
                sh "cd $WORKSPACE"
                sh "chmod u+x /script/Test.sh"
                sh "/script/Test.sh"
            }
        }
    }   
}