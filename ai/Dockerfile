FROM python:3.9
WORKDIR /zozo
COPY ./requirements.txt /zozo/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /zozo/requirements.txt
COPY ./main.py /zozo/main.py
COPY ./perfume_data.csv /zozo/perfume_data.csv
COPY ./recommend.py /zozo/recommend.py
COPY ./fashionPerfumes.csv /zozo/fashionPerfumes.csv
COPY ./model.h5 /zozo/model.h5
COPY ./model.h5 /zozo/model-for-male.h5
COPY ./model.h5 /zozo/model-for-all.h5
COPY ./model.py /zozo/model.py
COPY ./db.py /zozo/db.py
COPY ./.env /zozo/.env
CMD ["uvicorn", "main:app", "--proxy-headers", "--host", "0.0.0.0", "--port", "9876"]