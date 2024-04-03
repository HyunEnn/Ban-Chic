import { useEffect } from "react";
import LoadingSpinner from "../../../utils/LoadingSpinner";
import { useNavigate } from "react-router";
import Page_Url from "../../../router/Url";

function MainSelect() {
  const navigate = useNavigate();
  useEffect(() => {
    if (localStorage.getItem("accessToken")) {
      navigate(Page_Url.MainLogin);
    } else {
      navigate(Page_Url.NoLogin);
    }
  }, []);

  return <LoadingSpinner />;
}

export default MainSelect;
