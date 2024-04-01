import { useQuery } from "@tanstack/react-query";
import { getRecommendedPerfumeList } from "../../api/Api";

function useRecommended() {
  return useQuery({
    queryKey: ["myrecommed"],
    queryFn: () => getRecommendedPerfumeList(),
  });
}
export default useRecommended;
