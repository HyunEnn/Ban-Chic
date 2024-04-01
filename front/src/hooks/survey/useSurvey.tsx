import { useState } from "react";

function useSurvey(): [number[], (i: number) => void] {
  const [data, setData] = useState<number[]>([]);
  const selectPursuit = (i: number) => {
    if (data.includes(i)) {
      const number = data.filter((item) => item !== i);
      setData(number);
    } else if (data.length < 10) {
      const number = data;
      number.push(i);
      setData(number);
    }
  };

  return [data, selectPursuit];
}

export default useSurvey;
