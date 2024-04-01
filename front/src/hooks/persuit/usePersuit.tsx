import { useQuery } from "@tanstack/react-query";
import { getPersuit } from "../../api/Api";

function usePersuit() {
  return useQuery({
    queryKey: ["persuit"],
    queryFn: () => getPersuit(),
  });
}

export default usePersuit;
