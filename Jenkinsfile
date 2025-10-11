// pipeline {
//     agent any

//     tools {
//         jdk 'jdk-17'  
//         maven 'Maven3'
//     }

//     stages {
//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }

//         stage('Build and Test') {
//             steps {
//                 withMaven(maven: 'Maven3') {
//                     bat 'mvn clean verify'
//                 }
//             }
//         }

//         stage('Package') {
//             steps {
//                 bat 'mvn package'
//             }
//         }
//     }

//     post {
//         success {
//             echo 'Build and Tests SUCCESSFUL ✅'
//         }
//         failure {
//             echo 'Build FAILED ❌'
//         }
//     }
// }

pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/example/my-java-app.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            echo '✅ CI Passed: Build and Tests Successful'
        }
        failure {
            echo '❌ CI Failed: Check Build Logs'
        }
    }
}
