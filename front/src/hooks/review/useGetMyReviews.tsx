import { useQuery } from "@tanstack/react-query";
import { getReviews } from "../../api/Api";

export default function useGetMyReviews() {
  return useQuery({
    queryKey: ["myreviews"],
    queryFn: () => getReviews(Number(localStorage.getItem("uid"))),
  });
}
