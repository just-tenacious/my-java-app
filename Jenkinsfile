pipeline {
    agent any

    environment {
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/just-tenacious/my-java-app.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t shraddha15/myapp:%IMAGE_TAG% ."
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        docker push shraddha15/myapp:%IMAGE_TAG%
                    """
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                bat """
                ssh -o StrictHostKeyChecking=no user@192.168.3.211 ^
                "docker pull shraddha15/myapp:%IMAGE_TAG% && docker stop myapp || true && docker rm myapp || true && docker run -d -p 8080:8080 --name myapp shraddha15/myapp:%IMAGE_TAG%"
                """
            }
        }
    }

    post {
        success {
            echo '🚀 CDp Done: App deployed to server!'
        }
        failure {
            echo '❌ Deployment Failed! Check logs.'
        }
    }
}
