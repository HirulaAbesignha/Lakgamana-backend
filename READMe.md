
---

````markdown
# 🚀 Contribution Workflow – Lakgamana Backend

This project uses a **feature-branch workflow**.  
⚠️ Do not commit directly to `main` or `dev`.

---

## 🔧 Steps for Team Members

### 1. Fork the Repository
Go to 👉 [Lakgamana-backend](https://github.com/HirulaAbesignha/Lakgamana-backend) and click **Fork**.

---

### 2. Clone Your Fork
```bash
git clone https://github.com/<your-username>/Lakgamana-backend.git
cd Lakgamana-backend
````

---

### 3. Add the Upstream Remote (Original Repo)

```bash
git remote add upstream https://github.com/HirulaAbesignha/Lakgamana-backend.git
git fetch upstream
```

---

### 4. Checkout the `dev` Branch

```bash
git checkout -b dev upstream/dev
```

---

### 5. Checkout Your Assigned Feature Branch

The feature branches are already created in the main repo.
Each member should work on their assigned branch:

```bash
# Example (replace with your assigned branch)
git checkout -b feature/<feature-name> upstream/feature/<feature-name>
```

Available branches:

* `feature/ticket`
* `feature/reservation`
* `feature/payment`
* `feature/feedback`
* `feature/user`
* `feature/route`

---

### 6. Do Your Work

Make changes, then commit:

```bash
git add .
git commit -m "Implement <feature-name>"
```

---

### 7. Push to Your Fork

```bash
git push origin feature/<feature-name>
```

---

### 8. Open a Pull Request (PR)

1. Go to your fork on GitHub.
2. Click **New Pull Request**.
3. Set:

   * **base repository:** `HirulaAbesignha/Lakgamana-backend`
   * **base branch:** `dev`
   * **compare branch:** `feature/<feature-name>`
4. Submit PR for review.

---

### 9. Branch Cleanup

After your PR is merged:

```bash
git branch -d feature/<feature-name>
git push origin --delete feature/<feature-name>
```

---

✅ Summary:

* Work **only** on your assigned `feature/*` branch.
* Push changes to your fork.
* Open PR → base: `dev`.
