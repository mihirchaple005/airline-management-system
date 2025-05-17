pipeline {
    agent any

    environment {
        EC2_IP = '13.232.45.48'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/mihirchaple005/airline-management-system.git'
            }
        }

        stage('Build Microservices') {
            parallel {
                stage('Build Flight Service') {
                    steps {
                        dir('FlightMicroservice') {
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build Ticket Service') {
                    steps {
                        dir('TicketMicroservice') {
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build User Service') {
                    steps {
                        dir('UserMicroservice') {
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                bat 'docker-compose build'
            }
        }

        stage('Deploy Locally') {
            steps {
                script {
                    try {
                        bat 'docker-compose up -d'
                    } catch (err) {
                        echo "Local deployment failed: ${err}"
                    }
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                script {
                    withCredentials([sshUserPrivateKey(
                        credentialsId: 'ec2-honors',
                        keyFileVariable: 'SSH_KEY'
                    )]) {
                        bat """
                            scp -o StrictHostKeyChecking=no -i %%SSH_KEY%% docker-compose.yml ec2-user@${env.EC2_IP}:~/
                            ssh -o StrictHostKeyChecking=no -i %%SSH_KEY%% ec2-user@${env.EC2_IP} "docker-compose up -d"
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        failure {
            echo 'Pipeline failed! Check logs.'
        }
    }
}
