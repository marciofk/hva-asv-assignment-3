pipeline {
    agent any
    tools { 
        maven 'maven 3.6.3' 
        jdk 'JDK11' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage ('Deploy') {
            steps {
               sh 'cp target/*.war /Users/mfknr/software/apache-tomcat-9.0.35/webapps'
            }
        }
    }
}
