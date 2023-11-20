import {
  IconBoxMultiple,IconPigMoney, IconCircleDot , IconHome, IconInfoCircle, IconLayout, IconLayoutGrid, IconPhoto, IconPoint, IconStar, IconTable, IconUser
} from "@tabler/icons-react";

import { uniqueId } from "lodash";

const Menuitems = [
  {
    id: uniqueId(),
    title: "Dashboard",
    icon: IconHome,
    href: "/teacher",
  },
  {
    id: uniqueId(),
    title: "Workshop",
    icon: IconCircleDot,
    href: "/teacher/ui-components/buttons",
  },
  {
    id: uniqueId(),
    title: "Forms",
    icon: IconTable,
    href: "/teacher/ui-components/forms",
  },
  {
    id: uniqueId(),
    title: "Chat",
    icon: IconInfoCircle,
    href: "/teacher/ui-components/alerts",
  },
  // {
  //   id: uniqueId(),
  //   title: "Rate",
  //   icon: IconStar,
  //   href: "/teacher/ui-components/ratings",
  // },
  {
    id: uniqueId(),
    title: "Withdrawal",
    icon: IconPigMoney ,
    href: "/teacher/ui-components/images",
  },
  // {
  //   id: uniqueId(),
  //   title: "Pagination",
  //   icon: IconUser,
  //   href: "/ui-components/pagination",
  // },
  // {
  //   id: uniqueId(),
  //   title: "Tables",
  //   icon: IconLayoutGrid,
  //   href: "/ui-components/table",
  // },
];

export default Menuitems;
