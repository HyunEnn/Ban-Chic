import { useQuery } from "@tanstack/react-query";
import { getPerfumeDetail } from "../../api/Api";

export default function useGetPerfumeDetail(perfumeId: string) {
  return useQuery({
    queryKey: ["perfume"],
    queryFn: () => getPerfumeDetail(perfumeId),
  });
}
