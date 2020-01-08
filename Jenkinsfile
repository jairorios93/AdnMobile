pipeline {
    agent {
        label 'Slave4'
    }
      
    options { 
        buildDiscarder(logRotator(numToKeepStr: '3')) 
        disableConcurrentBuilds()       
    }
   
    tools {     
        jdk 'JDK8_Mac' 
    } 

    stages{     
        stage('Checkout'){ 
            steps{ 
                echo "------------>Checkout<------------" 
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitHub_jairorios93', url:'https://github.com/jairorios93/AdnMobile.git']]]) 
                sh 'chmod +x AlquilerVehiculos/gradlew'
                sh 'chmod +x AlquilerVehiculosFront/gradlew'
            } 
        }     

        stage('Build backend') {
            steps {
                dir ('AlquilerVehiculos/'){
                    sh './gradlew clean build'
                }
            }
        }
		
		stage('Unit Tests backend') { 
			steps{
				dir ('AlquilerVehiculos/'){
					sh 'gradlew --b build.gradle test --scan'
					sh 'gradlew --b build.gradle jacocoTestReport'    
				}
			}
		}
        
        stage('Build frontend') { 
            steps{     
                dir ('AlquilerVehiculosFront/'){
                    sh './gradlew clean build'
                }
            } 
        }

        stage('Static Code Analysis') {   
            steps{    
                withSonarQubeEnv('Sonar'){
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }       
            }     
        }

		stage('Build') {
			steps { 
				dir ('AlquilerVehiculos/'){
					sh 'gradlew --b build.gradle build -x test'
				}     
			}
		}
	 
    } 

    post { 
        always {  
            echo 'This will always run' 
        }     
        success {  
            echo 'This will run only if successful' 
            junit '**/test-results/test/*.xml'
        }  
        failure {   
            echo 'This will run only if failed'
            mail(to: 'jairo.rios@ceiba.com.co',
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is wrong with ${env.BUILD_URL}")
        }    
        unstable {      
            echo 'This will run only if the run was marked as unstable'    
        }     
        changed {      
            echo 'This will run only if the state of the Pipeline has changed'  
            echo 'For example, if the Pipeline was previously failing but is now successful'  
        } 
    } 
}