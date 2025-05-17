pipeline {
    agent any

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
                        dir('FlightMicroservice') {  // Changed from flight-service
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build Ticket Service') {
                    steps {
                        dir('TicketMicroservice') {  // Changed from ticket-service
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
                stage('Build User Service') {
                    steps {
                        dir('UserMicroservice') {  // Changed from user-service
                            bat 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    bat 'docker-compose build'
                }
            }
        }

        stage('Deploy') {
    steps {
        script {
            try {
                sh 'docker-compose up -d'
            } catch (err) {
                echo "Deployment failed: ${err}"
                // Optional: currentBuild.result = 'UNSTABLE'
            }
        }
    }
}

        stage('Deploy to EC2') {
    steps {
        script {
            def EC2_IP = '13.232.45.48' // elastic ip
            withCredentials([sshUserPrivateKey(
                credentialsId: 'ec2-honors',  
                keyFileVariable: 'SSH_KEY'     // Temp file path for key
            )]) {
                sh """
                    scp -o StrictHostKeyChecking=no -i $SSH_KEY docker-compose.yml ec2-user@${EC2_IP}:~/  
                    ssh -o StrictHostKeyChecking=no -i $SSH_KEY ec2-user@${EC2_IP} "docker-compose up -d"
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