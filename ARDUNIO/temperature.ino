const int sensorPin = A0; //pin analog pada sensor berada pada A0
float sensor = 0.0; //variabel yang berisikan nilai dari sensor
float sampel; //mengambil sampel suhu
float total_sampel = 0.0; //total dari 10 sampel yang diambil
float temperature = 0.0; //temperature yang ditampilkan
int i; //iterasi untuk menjumlahkan 10 sampel 

void setup(){
  pinMode(sensor, INPUT);
  Serial.begin(9600);
}

void loop(){
  for(i = 0; i < 10; i++){
    sensor = analogRead(sensorPin);
    sampel = ((sensor * 5.0 / 1024) - 0.5)*100; //mengubah nilai analog dari sensor ke besaran celcius
    delay(500);
    total_sampel = total_sampel + sampel;
  }
  temperature = (total_sampel/10); //mengambil rata-rata dari 10 sampel
  Serial.print(String("Suhu: "));
  Serial.print(temperature, 1);
  Serial.println(String(" C")); //menampilkan temperature dalam celcius 
  total_sampel = 0.0;
}
