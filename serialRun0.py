import serial

ser = serial.Serial('/dev/ttyACM0',9600)
byte = bytes('0', 'ascii')
ser.write("0".encode())