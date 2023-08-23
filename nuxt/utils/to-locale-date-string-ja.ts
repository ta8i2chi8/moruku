// Dateを、日本時間で2023-01-01という形式で出力
const toLocaleDateStringJa = (date: Date) => {
  return date.toLocaleDateString("ja-JP", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  })
  .split("/")
  .join("-");
};

export { 
  toLocaleDateStringJa,
};