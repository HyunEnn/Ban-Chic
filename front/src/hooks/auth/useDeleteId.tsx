import { deleteMember } from "../../api/Api";

function useDeleteId() {
  const deleteId = () => {
    deleteMember().then((res) => {
      console.log(res);
    });
  };
  return deleteId;
}

export default useDeleteId;
