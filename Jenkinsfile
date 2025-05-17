pipeline {
    agent any

    environment {
        IMAGE_NAME = "airline-app"
        DOCKER_REGISTRY = "mihirchaple"
        EC2_IP = "3.7.213.139"
        SSH_KEY = credentials('
MIIEpQIBAAKCAQEA38LSRmBV93mlZgAv9CByJPoa9DKt36AxRhRuNCtKyDO+h0SA
NSQ6BtwFe/9dMahN5gUFqCAjD+9cxFFP0nyF3/qQ7WIvKID6mPrhjNfLwrp55JWe
4Z1RvHan/LP3S6QBFLrgfAs6ULDBZCCbPoE+DzV6TagnMnDtfLpDITt/mHFgFoRl
8+4HUrmjxF4BkVcWh4+82TkNg0az5q66mNgB6q4QBL0JHX0/zRmumdNioMxilR42
2IHv1hRLDTAFmrz0PEovLkDR4gwu89vUmemvIgEW/g8qITgn2mO66pTZIt68/Cqk
/WgTJ9NBVniTEoWTaEOl26ED16xMoceaVALVbQIDAQABAoIBAQCJLbUf9Bt/9pHR
8Ld1YRbZWIOBxjgWau0KW6gcT5YvGpnp77Lx4V25qtWp6SpHMTlglR+crOcR89rZ
eEgJcNHaOJD1F0Z1/GylsNjqMbn3jb8AxkF7a6kac1wVbndjZg5LmS3anj+IDHTj
X6INNcrIJC7wwPAW2mieGMri6skUuPeJwKMQKTLOjjqvzT72QiHHfoc2lVWURe5Q
drpQpLam27ZGQjOWTW4DXoBnvy9vvB0GWzXL39IsXqSKFkMBQT4Lh2Nsm/rL3SlC
9cOTJvXWA1CyHR07N47xwcZLaSbJO/NC4vMFoo4ZFFwBnTS1h74nGjZs1nK2bv91
4vNFfSOBAoGBAPD+pq3jv7P5ITXeUc5KGvKm+5TZ3vpIwhIH/BFwl+rjdKY32EII
TFwM7VYXU2bRo93dcvVZSd4l1F/OL6asIWnhZEcTUJJzFeknokbhrEdVe8U+oZpk
mW6O3t1vhaK8wmUfX/jhL0NwiYOU+tLVCY1IODK22xPuu8HBNCrS2okdAoGBAO2x
eP2mNME5ZjZ6VDu3rL1Y3FetAE9xcd7U0LEWanqH08c/Qv1kN+jA8oFgPF93+Lan
yhFypNHuvNKo3hwigmpGeWQ2UJqGA8/Ew3ICiudkK96g5XEZjUO0Si6VjmbsQTGT
h3b6z8utvYucc5RpSwPnB2aCI0JXNYxPxXITbByRAoGAIFhNTrFunV0mk/BO0n0n
uMGoqoXDY5w4T+s6bazK9v+oPFbqc5JhjUk+4Xsn+njQoYqDT2K4g1RYeLNXuesI
YQmbR97TxZQksLJdGV5YnQpgHk0Vq38tCKWsS9QX/4uOG7xmSSUQDuEf08U/FOT6
7zduM4BPTxGPqaWgts6t/hECgYEAuB4J14kAQlBBAZ/+UYe0UbNccrm6jM8WYoI8
6X+wmRtPi35QWNFhkXSsJFU0Tssyp+IO3zcLvuYL864E5oxAsyvF6j88boWyvCTx
NAYOLLDmXnwJXnNpfnFQKQeGF+mXklxxyVL81QvO0C8vRF3PUsWJcPkLcN72xqog
Cn5K91ECgYEAxGFBMoNMdqRZuIUvmK5XF4AY1EKpu+trb/S8KE/OM2lKdg9/jjll
YXVvHzTvcIcUVW6P95aHhNSM+ruGVRD1nJbXuDfTQAfARPvQRWME2li48w2FoLmr
vLKC3hUZg7i3zbQy1tlmO+FJ9QAfaV0yPhgXL+pRNxTFI9DpmcE23mQ=
')  // Jenkins credentials ID
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
