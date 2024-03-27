FROM python:3.9
WORKDIR /zozo
COPY ./requirements.txt /zozo/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /zozo/requirements.txt
COPY ./main.py /zozo/main.py
COPY ./perfume_data.xls /zozo/perfume_data.xls
COPY ./recommend.py /zozo/recommend.py
CMD ["uvicorn", "main:app", "--proxy-headers", "--host", "0.0.0.0", "--port", "9876"]