FROM python:3.9
WORKDIR /zozo
COPY ./requirements.txt /zozo/requirements.txt
RUN pip install --upgrade pip
RUN pip install --no-cache-dir --upgrade -r /zozo/requirements.txt
COPY ./main.py /zozo/main.py
COPY ./perfume_data.csv /zozo/perfume_data.csv
COPY ./recommend.py /zozo/recommend.py
COPY ./fashionPerfumes.csv /zozo/fashionPerfumes.csv
COPY ./model.h5 /zozo/model.h5
CMD ["uvicorn", "main:app", "--proxy-headers", "--host", "0.0.0.0", "--port", "9876"]