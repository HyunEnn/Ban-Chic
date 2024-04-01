import { useQuery } from "@tanstack/react-query";
import { getPerfumeReviews } from "../../api/Api";

export default function useGetPerfumeReviews(perfumeId: string) {
  return useQuery({
    queryKey: ["perfumereviews"],
    queryFn: () => getPerfumeReviews(perfumeId),
  });
}
