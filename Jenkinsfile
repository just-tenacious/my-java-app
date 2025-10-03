pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    triggers {
        githubPush()
    }

    stages {
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
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Build and Tests SUCCESSFUL ✅'
        }
        failure {
            echo 'Build FAILED ❌'
        }
    }
}
