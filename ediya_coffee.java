from flask import Flask
app = Flask(__name__)

import requests
from bs4 import BeautifulSoup

from pymongo import MongoClient
import certifi
ca = certifi.where()
client = MongoClient('mongodb+srv://test:sparta@cluster0.xevhlvh.mongodb.net/Cluster0?retryWrites=true&w=majority', tlsCAFile=ca)
db = client.dbsparta

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36'}
    
    
data = requests.get('https://ediya.com/contents/drink.html?chked_val=12,&skeyword=#blockcate', headers=headers)
soup = BeautifulSoup(data.text, 'html.parser')
coffees = soup.select('#menu_ul > li')

for coffee in coffees:
    name = coffee.select_one('div.menu_tt > a:nth-child(1) > span').text
    img = coffee.select_one('li > a > img')
    img_src = img.get("src")
    calorie = coffee.select_one('div.pro_detail > div.pro_comp > div.pro_nutri > dl > dd').text.replace("(","").replace(")","")
    sugar = coffee.select_one('div.pro_detail > div.pro_comp > div.pro_nutri > dl:nth-child(2) > dd').text.replace("(","").replace(")","")
    protein = coffee.select_one('div.pro_detail > div.pro_comp > div.pro_nutri > dl:nth-child(3) > dd').text.replace("(","").replace(")","")
    protein = coffee.select_one('div.pro_detail > div.pro_comp > div.pro_nutri > dl:nth-child(3) > dd').text.replace(
        "(", "").replace(")", "")
    fat = coffee.select_one('div.pro_detail > div.pro_comp > div.pro_nutri > dl:nth-child(4) > dd').text.replace(
        "(", "").replace(")", "")
    salt = coffee.select_one('div.pro_detail > div.pro_comp > div.pro_nutri > dl:nth-child(5) > dd').text.replace(
        "(", "").replace(")", "")
    caffeine = coffee.select_one('div.pro_detail > div.pro_comp > div.pro_nutri > dl:nth-child(6) > dd').text.replace(
        "(", "").replace(")", "")
    print(name)
    print("https://ediya.com/" + img_src)
    print("칼로리 " + calorie)
    print("당류 " + sugar)
    print("단백질 " + protein)
    print("지방 " + fat)
    print("나트륨 " + salt)
    print("카페인 " + caffeine)
    print()
