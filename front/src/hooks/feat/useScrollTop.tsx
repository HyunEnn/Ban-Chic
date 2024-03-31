function useScrollTop() {
  const GoToTop = () => {
    document.getElementById("root")?.scrollIntoView({ behavior: "smooth" });
  };
  return GoToTop;
}

export default useScrollTop;
