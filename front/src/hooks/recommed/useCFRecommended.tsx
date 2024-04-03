import { useQuery } from "@tanstack/react-query";
import { getCFRecommend } from "../../api/Api";

function useRecommended() {
  return useQuery({
    queryKey: ["mycfrecommed"],
    queryFn: () => getCFRecommend(),
  });
}
export default useRecommended;
