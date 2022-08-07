// E.g. declarative pipeline
// pipeline {
//     agent any
//     stages {
//         stage('JunitTest') {
//             steps {
//                 sh "cd $WORKSPACE"
//                 sh "chmod u+x /script/TDD.sh"
//                 sh "/script/TDD.sh"
//             }
//         }
//     }   
// }
// Pipleine scripts can be test in the Jenkins UI, if it works then change to run this Jenkinsfile when github push event happens.
node {
    // Must requirement, to get trigger by the github webhook.
    stage ('Initialization'){
        git branch: 'test', url: 'https://github.com/wen2720/DTUpay.git' //make local test branch
    }
    // Jenkins gets github credential to run git commands
    //stage ('fetch'){
    //    withCredentials([gitUsernamePassword(credentialsId: 'githubPATJenkins')]) {
    //        sh ("git fetch --all")
    //    }
    //}
    stage ('JunitTest'){
        // The following steps can be written into shell scirpt without the Jenkins interpretation.
        sh("java -cp \".:./lib/*\" org.junit.runner.JUnitCore model.TestStringCalculator")
        sh ("git switch test")
        sh ("git pull origin test")
        sh ("git branch main")
        sh ("git switch main")
        sh ("git pull origin main")
        sh ("git merge test")
        withCredentials([gitUsernamePassword(credentialsId: 'githubPATJenkins')]) {
            sh ("git push origin main")
        }
    }
}