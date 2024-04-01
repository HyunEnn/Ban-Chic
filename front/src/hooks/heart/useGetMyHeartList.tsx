import { useQuery } from "@tanstack/react-query";
import { getLikes } from "../../api/Api";

export default function useGetMyHeartList() {
  return useQuery({
    queryKey: ["myheartlist"],
    queryFn: () => getLikes(Number(localStorage.getItem("uid"))),
  });
}
