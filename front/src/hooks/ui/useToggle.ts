import { useCallback, useState } from "react";

export function useToggle(): {
  isOpen: boolean;
  toggleMenu: () => void;
} {
  const [isOpen, setIsOpen] = useState(false);

  // button 클릭 시 토글
  const toggleMenu = useCallback(() => {
    setIsOpen(!isOpen);
  }, [isOpen]);

  return { isOpen, toggleMenu };
}

export default useToggle;
