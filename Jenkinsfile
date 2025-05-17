pipeline {
    agent any

    environment {
        IMAGE_NAME = "airline-app"
        DOCKER_REGISTRY = "mihirchaple"
        EC2_IP = "3.7.213.139"
        SSH_KEY = credentials('MIIEpQIBAAKCAQEA38LSRmBV93mlZgAv9CByJPoa9DKt36AxRhRuNCtKyDO+h0SANSQ6BtwFe/9dMahN5gUFqCAjD+9cxFFP0nyF3/qQ7WIvKID6mPrhjNfLwrp55JWe4Z1RvHan/LP3S6QBFLrgfAs6ULDBZCCbPoE+DzV6TagnMnDtfLpDITt/mHFgFoRl8+4HUrmjxF4BkVcWh4+82TkNg0az5q66mNgB6q4QBL0JHX0/zRmumdNioMxilR422IHv1hRLDTAFmrz0PEovLkDR4gwu89vUmemvIgEW/g8qITgn2mO66pTZIt68/Cqk/WgTJ9NBVniTEoWTaEOl26ED16xMoceaVALVbQIDAQABAoIBAQCJLbUf9Bt/9pHR8Ld1YRbZWIOBxjgWau0KW6gcT5YvGpnp77Lx4V25qtWp6SpHMTlglR+crOcR89rZeEgJcNHaOJD1F0Z1/GylsNjqMbn3jb8AxkF7a6kac1wVbndjZg5LmS3anj+IDHTjX6INNcrIJC7wwPAW2mieGMri6skUuPeJwKMQKTLOjjqvzT72QiHHfoc2lVWURe5QdrpQpLam27ZGQjOWTW4DXoBnvy9vvB0GWzXL39IsXqSKFkMBQT4Lh2Nsm/rL3SlC9cOTJvXWA1CyHR07N47xwcZLaSbJO/NC4vMFoo4ZFFwBnTS1h74nGjZs1nK2bv914vNFfSOBAoGBAPD+pq3jv7P5ITXeUc5KGvKm+5TZ3vpIwhIH/BFwl+rjdKY32EIITFwM7VYXU2bRo93dcvVZSd4l1F/OL6asIWnhZEcTUJJzFeknokbhrEdVe8U+oZpkmW6O3t1vhaK8wmUfX/jhL0NwiYOU+tLVCY1IODK22xPuu8HBNCrS2okdAoGBAO2xeP2mNME5ZjZ6VDu3rL1Y3FetAE9xcd7U0LEWanqH08c/Qv1kN+jA8oFgPF93+LanyhFypNHuvNKo3hwigmpGeWQ2UJqGA8/Ew3ICiudkK96g5XEZjUO0Si6VjmbsQTGTh3b6z8utvYucc5RpSwPnB2aCI0JXNYxPxXITbByRAoGAIFhNTrFunV0mk/BO0n0nuMGoqoXDY5w4T+s6bazK9v+oPFbqc5JhjUk+4Xsn+njQoYqDT2K4g1RYeLNXuesIYQmbR97TxZQksLJdGV5YnQpgHk0Vq38tCKWsS9QX/4uOG7xmSSUQDuEf08U/FOT67zduM4BPTxGPqaWgts6t/hECgYEAuB4J14kAQlBBAZ/+UYe0UbNccrm6jM8WYoI86X+wmRtPi35QWNFhkXSsJFU0Tssyp+IO3zcLvuYL864E5oxAsyvF6j88boWyvCTxNAYOLLDmXnwJXnNpfnFQKQeGF+mXklxxyVL81QvO0C8vRF3PUsWJcPkLcN72xqogCn5K91ECgYEAxGFBMoNMdqRZuIUvmK5XF4AY1EKpu+trb/S8KE/OM2lKdg9/jjllYXVvHzTvcIcUVW6P95aHhNSM+ruGVRD1nJbXuDfTQAfARPvQRWME2li48w2FoLmrvLKC3hUZg7i3zbQy1tlmO+FJ9QAfaV0yPhgXL+pRNxTFI9DpmcE23mQ=')  // Jenkins credentials ID
    }

    triggers {
        githubPush()
    }

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/mihirchaple005/airline-management-system.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([ credentialsId: 'dockerhub-creds' ]) {
                    sh '''
                        docker tag $IMAGE_NAME $DOCKER_REGISTRY/$IMAGE_NAME
                        docker push $DOCKER_REGISTRY/$IMAGE_NAME
                    '''
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sshagent (credentials: ['ec2-ssh-key']) {
                    sh '''
                    ssh -o StrictHostKeyChecking=no ubuntu@$EC2_IP '
                        docker pull $DOCKER_REGISTRY/$IMAGE_NAME &&
                        docker stop airline || true &&
                        docker rm airline || true &&
                        docker run -d --name airline -p 8080:8080 $DOCKER_REGISTRY/$IMAGE_NAME
                    '
                    '''
                }
            }
        }
    }
}
