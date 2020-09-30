import serial
import time
import requests
import json
firebase_url = "https://*****.firebaseio.com"
ser = serial.Serial('COM3', 9600, timeout=100)

fixed_interval = 1

while 1:
	try:
		#membaca data dari seria port
		ser.flush()
		sensor = ser.readline()
		#variabel waktu waktu
		times = time.strftime('%H:%M:%S')
		date = time.strftime('%d/%m/%Y')
		#memasukkan date dan time pada list waktu
		waktu = { 'date': date, 'time': times}
		#split value dari varibel sensor karena ada karakter yang tidak bisa di decode
		sample = sensor.split()
		#ambil index 1 dan 2 dari sample
		par = sample[1:3]
		#menggabungkan kembali dan di decode menjadi string
		x = (b' '.join(par)).decode("utf-8")
		print(x)
		#memasukkan value dari variabel waktu dan x ke ist data
		data = {'waktu' : waktu, 'suhu' : x}
		#mengirim data ke firebase menggunakan POST Method
		result = requests.put(firebase_url + '/sensor1.json', data=json.dumps(data))
		print('Record inserted. Result Code = ' + str(result.status_code) + ',' + result.text)	
	except IOError:
		print('Error')