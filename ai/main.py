from fastapi import FastAPI, File, UploadFile
from pydantic import BaseModel
from recommend import get_recommend_perfume, get_recommend_fashionPerfume, find_similar_users
from PIL import Image
from io import BytesIO
Image.MAX_IMAGE_PIXELS = None

app = FastAPI()

class Member(BaseModel):
    member_id : int

class Item(BaseModel):
    casual: int
    chic: int
    classic: int
    clear: int
    coolcasual: int
    dandy: int
    dynamic: int
    elegant: int
    gorgeous: int
    modern: int
    natural: int
    pretty: int
    romantic: int
    wild: int

# 추구미를 받아서 향수를 추천받는 Endpoint
@app.post("/api/recommend")
def read_item(item: Item):
    perfumes = get_recommend_perfume(item)
    return {"recommend_index": perfumes}
@app.post("/api/image")
async def api_file(file: UploadFile = File(...)):
    # await file.seek(0)
    contents =await file.read()
    pred_img = Image.open(BytesIO(contents))
    return get_recommend_fashionPerfume(pred_img)

@app.post("/api/user")
def read_item(member: Member):
    print("hello")
    return {"recommend_index": find_similar_users(member.member_id)}