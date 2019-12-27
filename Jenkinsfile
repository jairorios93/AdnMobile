pipeline {   

 agent any
  
 options { 
   buildDiscarder(logRotator(numToKeepStr: '3')) 
   disableConcurrentBuilds()   
 } 
   
 tools {     
   jdk 'JDK8_Centos' 
   gradle 'Gradle5.6_Centos'
 } 
 
 stages{     
    stage('Checkout'){ 
	 steps{ 
	  echo "------------>Checkout<------------" 
	  checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 
		'GitHub_jairorios93', url:'https://github.com/jairorios93/AdnMobile.git']]]) 
     } 
    }     
    
	stage('Build project') {
	 steps {
	  sh 'gradle --b ./AlquilerVehiculos/build.gradle clean'
	  sh 'gradle --b ./AlquilerVehiculos/build.gradle build'
	  
	  bat './AlquilerVehiculosFront/gradlew --b ./AlquilerVehiculosFront/build.gradle clean build -x :app:test'
	 }
	}
	
    stage('Compile & Unit Tests') { 
     steps{     
      echo "------------>Unit Tests<------------" 
	  sh 'gradle --b ./AlquilerVehiculos/build.gradle test --scan'
	  sh 'gradle --b ./AlquilerVehiculos/build.gradle jacocoTestReport'   
     }
    } 
     
     stage('Static Code Analysis') {   
      steps{    
       echo '----------------->Analisis de Código estático<-----------------'
        withSonarQubeEnv('Sonar'){
        sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
        }       
       }     
      } 
      
     stage('Build') {
      steps { 
       echo "-------->Build<---------"
       sh 'gradle --b ./AlquilerVehiculos/build.gradle build -x test'
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
